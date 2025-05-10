package ntu.ngokhaidang.appdoannhac;

public class Question {
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctAnswerIndex;
    private String musicName;  // Tên file nhạc trong res/raw
    private String imageName;  // Tên file hình trong res/drawable

    // Constructor trống để Firebase tự tạo đối tượng
    public Question() {}

    // Constructor đầy đủ
    public Question(String option1, String option2, String option3, String option4,
                    int correctAnswerIndex, String musicName, String imageName) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswerIndex = correctAnswerIndex;
        this.musicName = musicName;
        this.imageName = imageName;
    }

    // Getter và Setter
    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
