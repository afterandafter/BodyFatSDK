package com.scale.bluetoothlibrary.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.scale.bluetoothlibrary.util.DataUtil;
import com.scale.bluetoothlibrary.util.StringUtil;

import java.util.zip.DataFormatException;

public class BluetoothUtil {
    private static BluetoothUtil bluetoothUtil;
    public BluetoothManager mBluetoothManager;
    public BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner scanner;
    private BluetoothSearchListener bluetoothSearchListener;
    public static int REQUEST_CODE_ACCESS_COARSE_LOCATION = 1;
    public static int BLUETOOTH_DISCOVERABLE_RESULT = 2;
    private int serialNumber = 0;
    private String mac;
    private String startWith = "10 FF";
    private String TAG = "BluetoothUtil";
    private boolean searchStatus;

    public static BluetoothUtil getInstance() {
        if (bluetoothUtil == null) {
            bluetoothUtil = new BluetoothUtil();
        }
        return bluetoothUtil;
    }

    public interface BluetoothSearchListener {
        void onSearchCallback(DeviceConfig deviceBean);
    }

    public void registerBluetoothListener(BluetoothSearchListener bluetoothSearchListener) {
        this.bluetoothSearchListener = bluetoothSearchListener;
    }

    public void init(Context context) {
        if (mBluetoothManager == null) {
            mBluetoothManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        }
        if (mBluetoothAdapter == null) {
            mBluetoothAdapter = mBluetoothManager.getAdapter();
        }
    }

    /**
     * 搜索部分
     */
    public void searchDevice(Object mac) {
        if (searchStatus) return;
        this.mac = "";
        if (mac instanceof String) {
            this.mac = (String) mac;
        }
        if (mBluetoothManager == null || mBluetoothAdapter == null) {
            return;
        }
        scanner = mBluetoothAdapter.getBluetoothLeScanner();
        ScanSettings.Builder settings = new ScanSettings.Builder();
        settings.setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);
        if (scanner != null) {
            Log.e(TAG, "startScan");
            searchStatus = true;
            scanner.startScan(null, settings.build(), leCallback);
        }
    }

    /**
     * 搜索回调
     */
    private ScanCallback leCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if (TextUtils.isEmpty(mac) || result.getDevice().getAddress().equals(mac.toUpperCase())) {
                parsingData(result);
            }
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            stopSearchDevice();
            Log.e("TAG", "scanFailed=" + errorCode);
        }
    };

    private void parsingData(ScanResult result) {
        byte[] scanRecord = result.getScanRecord().getBytes();
        if (StringUtil.bytes2HexString(scanRecord).startsWith(startWith)) {
            if (serialNumber != (scanRecord[3] & 0xff)) {
                if (scanRecord.length >= 11 && (StringUtil.getBit(scanRecord[10], 0) == 1)) {
                    serialNumber = scanRecord[3] & 0xff;
                    float decimal = DataUtil.getDecimal(StringUtil.getTwoBit(scanRecord[10], 5, 7));//保留的小数点位数
                    double weight = DataUtil.getWeight(scanRecord, decimal);//体重
                    int impedance = (scanRecord[6] & 0xff) * 256 + (scanRecord[7] & 0xff);
                    DeviceConfig deviceConfig = new DeviceConfig();
                    deviceConfig.setRssi(result.getRssi());
                    deviceConfig.setName(result.getDevice().getName());
                    deviceConfig.setAddress(result.getDevice().getAddress());
                    deviceConfig.setWeight(weight);
                    deviceConfig.setUnit("kg");
                    deviceConfig.setImpedance(impedance);
                    deviceConfig.setData(StringUtil.bytes2HexString(scanRecord));
                    bluetoothSearchListener.onSearchCallback(deviceConfig);
                    stopSearchDevice();
                }
            }
        }
    }

    public void stopSearchDevice() {
        if (scanner != null) {
            scanner.stopScan(leCallback);
            searchStatus = false;
            Log.e("TAG", "stopSearch");
        }
    }
}