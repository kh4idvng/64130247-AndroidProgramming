package thigk2.ngokhaidang;

public class KyNiem {
    String tenFileAnh;
    String CaptionAnh;

    public KyNiem(String tenFileAnh, String captionAnh) {
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

