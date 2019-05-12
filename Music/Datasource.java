import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class Datasource {
	public static final String DB_NAME = "music.db";
	public static final String CONNECTION_STRING = "jdbc:sqlite:/home/ubuntu/Desktop/Java_Projects/Music/" + DB_NAME;
	public static final String TABLE_ALBUMS = "albums";
	public static final String COLUMN_ALBUM_ID = "_id";
	public static final String COLUMN_ALBUM_NAME = "name";
	public static final String COLUMN_ALBUM_ARTIST = "artist";
	public static final int INDEX_ALBUM_ID = 1;
	public static final int INDEX_ALBUM_NAME = 2;
	public static final int INDEX_ALBUM_ARTIST = 3;


	public static final String TABLE_ARTISTS = "artists";
	public static final String COLUMN_ARTISTS_ID = "_id";
	public static final String COLUMN_ARTISTS_NAME = "name";
	public static final int INDEX_ARTISTS_ID = 1;
	public static final int INDEX_ARTISTS_NAME = 2;

	
	public static final String TABLE_SONGS = "songs";
	public static final String COLUMN_SONGS_ID = "_id";
	public static final String COLUMN_SONGS_TRACK = "track";
	public static final String COLUMN_SONGS_TITLE = "title";
	public static final String COLUMN_SONGS_ALBUM = "album";
	public static final int INDEX_SONGS_ID = 1;
	public static final int INDEX_SONGS_TRACK = 2;
	public static final int INDEX_SONGS_TITLE = 3;
	public static final int INDEX_SONGS_ALBUM = 4;

	public static final int ORDER_BY_NONE = 1;
	public static final int ORDER_BY_ASC = 2;
	public static final int ORDER_BY_DESC = 3;

	private Connection conn;

	public boolean open() {
		try {
			conn = DriverManager.getConnection(CONNECTION_STRING);
			return true;
		} catch(SQLException e){
			System.out.println("Couldn't connect to database: " + e.getMessage());
			return false;
		}
	}

	public void close() {
		try{
			if(conn != null){
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("Couldn't close connection: " + e.getMessage());
		}
	}

	public List<Artist> queryArtists(int sortOrder) {
/*		Statement statement = null;
		ResultSet results = null;*/

		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		sb.append(TABLE_ARTISTS);
		if(sortOrder != ORDER_BY_NONE) {
			sb.append(" ORDER BY ");
			sb.append(COLUMN_ARTISTS_NAME);
			sb.append(" COLLATE NOCASE ");
			if(sortOrder == ORDER_BY_DESC) {
				sb.append("DESC");
			} else {
				sb.append("ASC");
			}
		}

		try(Statement statement = conn.createStatement();
			ResultSet results = statement.executeQuery(sb.toString())) {

			List<Artist> artists = new ArrayList<>();
			while(results.next()) {
				Artist artist = new Artist();
				artist.setId(results.getInt(INDEX_ARTISTS_ID));
				artist.setName(results.getString(INDEX_ARTISTS_NAME));
				artists.add(artist);
			}

			return artists;

		} catch(SQLException e) {
			System.out.println("Query failed: " + e.getMessage());
			return null;
		}
	}


}