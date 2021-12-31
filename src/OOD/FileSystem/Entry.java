package OOD.FileSystem;

public abstract class Entry {
    Directory parent;
    long created;
    long lastUpdated;
    long lastAccessed;
    String name;

    public Entry(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        created = System.currentTimeMillis();
    }

    public boolean deleted() {
        if(parent == null) {
            return false;
        }
        return parent.deleteEntry(this);
    }

    public abstract int size();

    public String getFullPath() {
        if(parent == null) {
            return name;
        } else {
            return parent.getFullPath() + "/" + name;
        }
    }

    public long getCreatedTime() {
        return created;
    }

    public long getLastUpdatedTime() {
        return lastUpdated;
    }

    public long getLastAccessedTime() {
        return lastAccessed;
    }

    public void changeName(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

}
