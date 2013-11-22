/**
 * 
 */
package com.captech.walk2.rottentomatoes;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.captech.walk2.transformers.Transformer;

/**
 * @author Macon
 *
 */
@Component
public class RottenMoviesToCelebrityTransformer implements Transformer<JSONArray, List<Celebrity>> {

	@Override
	public List<Celebrity> transform(JSONArray jsonMovies) {
		
		List<Celebrity> celebs = new ArrayList<Celebrity>();
		
		for (int index = 0; index < jsonMovies.length(); index++) {
			try {
				JSONObject aMovie = jsonMovies.getJSONObject(index);
				JSONArray cast = aMovie.getJSONArray("abridged_cast");
				for (int castIndex = 0; castIndex < cast.length(); castIndex++) {
					JSONObject castMember = cast.getJSONObject(castIndex);
					Celebrity celeb = new Celebrity();
					celeb.setName(castMember.getString("name"));
					celeb.setMovieTitle(aMovie.getString("title"));
					
					celebs.add(celeb);
				}
			}
			catch (Exception ex) { // swallow and move on to the next.
				
			}
		}
		
		return celebs;
	}

}
