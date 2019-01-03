package www.leigq.com.blackboxmusic.model;

/**
 * 歌曲实体
 * <p>
 * 创建人：asus <br>
 * 创建时间：2018-12-24 14:22 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class Song {
    private int id;

    /**
     * 名称
     */
    private String name;
    /**
     * 歌手
     */
    private String singer;
    /**
     * 专辑
     */
    private String album;
    /**
     * 大小
     */
    private long size;
    /**
     * 时长
     */
    private int duration;
    /**
     * 地址
     */
    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", singer='" + singer + '\'' +
                ", album='" + album + '\'' +
                ", size=" + size +
                ", duration=" + duration +
                ", path='" + path + '\'' +
                '}';
    }
}
