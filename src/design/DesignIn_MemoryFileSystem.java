package design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Design a data structure that simulates an in-memory file system.
 *
 * Implement the FileSystem class:
 *
 *     FileSystem() Initializes the object of the system.
 *     List<String> ls(String path)
 *         If path is a file path, returns a list that only contains this file's name.
 *         If path is a directory path, returns the list of file and directory names in this directory.
 *     The answer should in lexicographic order.
 *     void mkdir(String path) Makes a new directory according to the given path. The given directory path does not exist. If the middle directories in the path do not exist, you should create them as well.
 *     void addContentToFile(String filePath, String content)
 *         If filePath does not exist, creates that file containing given content.
 *         If filePath already exists, appends the given content to original content.
 *     String readContentFromFile(String filePath) Returns the content in the file at filePath.
 */
public class DesignIn_MemoryFileSystem {
    class FileSystem {
        //因为要根据文件路径名称找文件或者目录，所以需要一个字符串到文件/目录的映射，
        //文件和目录用一个File class来抽象，根据内容是否为空来判断是否为目录或者文件
        class File {
            StringBuilder content;
            String name;
            TreeMap<String, File> children;//因为要求输出有顺序，所以目录下的文件以及子目录存在treemap里
            File(String name) {
                this.name = name;
                children = new TreeMap<>();
                content = new StringBuilder();
            }

            public String getContent() {
                return content.toString();
            }

            public boolean isFile() {
                return content.length() > 0;
            }

            public String getName() {
                return name;
            }

            public void addContent(String c) {
                content.append(c);
            }

            public List<String> getList() {
                List<String> res = new ArrayList<>();
                if(isFile()) {//如果当前是一个文件，则返回包含它名字的列表
                    res.add(getName());
                } else {//如果当前是一个目录，则把它名下的所有文件或者目录按字母顺序放进列表返回。
                    res.addAll(children.keySet());
                }
                return res;
            }
        }

        File root;
        public FileSystem() {
            root = new File("");//初始化，根目录名字为空串
        }

        public List<String> ls(String path) {
            return findAndBuildFile(path).getList();
        }

        public void mkdir(String path) {
            findAndBuildFile(path);
        }

        public void addContentToFile(String filePath, String content) {
            findAndBuildFile(filePath).addContent(content);
        }

        public String readContentFromFile(String filePath) {
            return findAndBuildFile(filePath).getContent();
        }
        //最重要的一个函数，用来从根开始按照所给路径查找文件，如果不存在路径中所给的目录或文件，需要创建一个
        private File findAndBuildFile(String path) {
            String[] files = path.split("/");
            File cur = root;
            for(String file : files) {
                if(file.isEmpty()) continue;
                cur = cur.children.computeIfAbsent(file, k -> new File(file));//如果还不存在名为"file"的file,创建一个

                // if(cur.isFile()) break;//为了严谨，可以加这一行，根据题意，参数都是合法的，所以实际可以不用加
            }
            return cur;
        }
    }
}
