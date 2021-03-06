package OOD.FileSystem;

public class File extends Entry{
    private String content;
    private int size;

    public File(String name, Directory parent, int size) {
        super(name, parent);
        this.size = size;
    }

    public int size() {
        return size;
    }

    public String getContents() {
        return content;
    }

    public void setContents(String content) {
        this.content = content;
    }
}
