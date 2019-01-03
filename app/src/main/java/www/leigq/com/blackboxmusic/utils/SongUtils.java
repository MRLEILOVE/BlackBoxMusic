package www.leigq.com.blackboxmusic.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import www.leigq.com.blackboxmusic.model.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 歌曲工具类
 * <p>
 * 创建人：asus <br>
 * 创建时间：2018-12-24 15:51 <br>
 * <p>
 * 修改人： <br>
 * 修改时间： <br>
 * 修改备注： <br>
 * </p>
 */
public class SongUtils {

    /**
     * 获取所有本地音乐
     * @param context
     * @return
     */
    public static List<Song> getAllMusic(Context context) {
        //定义一个集合，存放从本地读取到的内容
        List<Song> list = new ArrayList<>();

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.AudioColumns.IS_MUSIC);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Song song = new Song();
                song.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
                song.setSinger(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                song.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                song.setDuration(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
                song.setSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                //把歌曲名字和歌手切割开
                if (song.getSize() > 1000 * 800) {
                    if (song.getName().contains("-")) {
                        String[] str = song.getName().split("-");
                        song.setName(str[0]);
                        song.setSinger(str[1]);
                    }
                    list.add(song);
                }
            }
        }
        assert cursor != null;
        cursor.close();
        return list;
    }

    //转换歌曲时间的格式
    public static String formatTime(int time) {
        if (time / 1000 % 60 < 10) {
            String tt = time / 1000 / 60 + ":0" + time / 1000 % 60;
            return tt;
        } else {
            String tt = time / 1000 / 60 + ":" + time / 1000 % 60;
            return tt;
        }
    }

}
