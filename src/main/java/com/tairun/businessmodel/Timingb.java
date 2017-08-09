package com.tairun.businessmodel;

/**
 * Created by THINK on 2017/8/2.
 */
public class Timingb extends IdenAction{
    int isNewVersion;

    public int getIsNewVersion() {
        return isNewVersion;
    }

    public void setIsNewVersion(int isNewVersion) {
        this.isNewVersion = isNewVersion;
    }

    public int getIsNewAdPic() {
        return isNewAdPic;
    }

    public void setIsNewAdPic(int isNewAdPic) {
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

    int isNewAdPic;
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
