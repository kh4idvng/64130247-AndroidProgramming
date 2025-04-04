package ntu.ngokhaidang.tuluyentap;

public class PhongCanh {
    String tenFileAnh;
    String CaptionAnh;

    public PhongCanh(String tenFileAnh, String captionAnh) {
        this.tenFileAnh = tenFileAnh;
        CaptionAnh = captionAnh;
    }

    public String getTenFileAnh() {
        return tenFileAnh;
    }

    public void setTenFileAnh(String tenFileAnh) {
        this.tenFileAnh = tenFileAnh;
    }

    public String getCaptionAnh() {
        return CaptionAnh;
    }

    public void setCaptionAnh(String captionAnh) {
        CaptionAnh = captionAnh;
    }
}
