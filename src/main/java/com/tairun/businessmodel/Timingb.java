package com.tairun.businessmodel;

/**
 * Created by THINK on 2017/8/2.
 */
public class Timingb extends IdenAction{
    String isNewVersion;
    int box_temperature;
    String newVersionMD5;

    public String getNewVersionMD5() {
        return newVersionMD5;
    }

    public void setNewVersionMD5(String newVersionMD5) {
        this.newVersionMD5 = newVersionMD5;
    }

    public int getBox_temperature() {
        return box_temperature;
    }

    public void setBox_temperature(int box_temperature) {
        this.box_temperature = box_temperature;
    }

    public String getIsNewVersion() {
        return isNewVersion;
    }

    public void setIsNewVersion(String isNewVersion) {
        this.isNewVersion = isNewVersion;
    }

    public String getIsNewAdPic() {
        return isNewAdPic;
    }

    public void setIsNewAdPic(String isNewAdPic) {
        this.isNewAdPic = isNewAdPic;
    }

    public String getNewVersionUrl() {
        return newVersionUrl;
    }

    public void setNewVersionUrl(String newVersionUrl) {
        this.newVersionUrl = newVersionUrl;
    }

    public String getNewPicUrl() {
        return newPicUrl;
    }

    public void setNewPicUrl(String newPicUrl) {
        this.newPicUrl = newPicUrl;
    }

    String isNewAdPic;
    String newVersionUrl;
    String newPicUrl;
    String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
