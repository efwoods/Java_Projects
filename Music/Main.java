import java.util.List;

public class Main{
	public static void main(String[] args){
		Datasource datasource = new Datasource();
		if(!datasource.open()) {
			System.out.println("Can't open datasource.");
			return;
		}

		List<Artist> artists = datasource.queryArtists(Datasource.ORDER_BY_DESC);
		if(artists == null){
			System.out.println("No artists.");
			return;
		}

		for(Artist artist : artists) {
			System.out.println("ID = " + artist.getId() + ", Name = " + artist.getName());
		}

		List<String> albums = datasource.queryAlbumsForArtist("Carole King", Datasource.ORDER_BY_NONE);
		if(albums == null){
			System.out.println("No albums by " + "Carole King");
			return;
		}

		for(String album : albums){
			System.out.println(album);
		}

		datasource.close();
	}
}