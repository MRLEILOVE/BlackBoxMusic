package www.leigq.com.blackboxmusic.model;

import java.util.List;

/**
 *  榜单实体
 * <p>
 * 创建人：asus <br>
 * 创建时间：2018-12-24 13:41 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class Leaderboard {

    private int id;
    private String img;
    private String name;
    private String desc;
    private List<Song> songs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", songs=" + songs +
                '}';
    }
}
