package utils;

public class UploadUtils {
    /**
     * 获取真实文件名
     * @param fileName
     * @return
     */
    public static String getShortFileName(String fileName) {
        int index = fileName.lastIndexOf("\\");
        if (index == -1) {
            return fileName;
        }
        return fileName.substring(index + 1);
    }

    /**
     * 随机生成文件名
     * @param fileName
     * @return
     */
    public static String generateRandomFileName(String fileName) {
        // 获取扩展名
        String ext = fileName.substring(fileName.lastIndexOf("."));
        return StringUtils.uuid() + ext;
    }

    /**
     * 利用uuidFileName的hashCode生成二级目录
     * @param uuidFileName
     * @return
     */
    public static String generateRandomDir(String uuidFileName) {
        int hashCode = uuidFileName.hashCode();
        // 一级目录
        int dir1 = hashCode & 0xf;
        // 二级目录
        int dir2 = (hashCode >> 4) & 0xf;
        return "/" + dir1 + "/" + dir2;
    }
}
