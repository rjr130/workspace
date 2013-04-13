///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  PhotoDBApp.java
// File:             PhotoDatabase.java
// Semester:         Spring 2012
//
// Author:           Chew Wei Lai 	clai9@wisc.edu
// CS Login:         clai
// Lecturer's Name:  Beck Hasti
// Lab Section:      none
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     none
// CS Login:         none
// Lecturer's Name:  none
// Lab Section:      none
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;
public class PhotoDatabase implements Iterable<Photo>{
	List<Photo> photoList;

	PhotoDatabase(){
		photoList = new ArrayList<Photo>();
	}
	/**
	 * Add a Photo with the given name n to the end of the database. If a Photo with the name n is already in the database, just return.
	 *
	 * @param n Name of Photo
	 *  */
	public void addPhoto(String p){
		if(p == null){
			throw new IllegalArgumentException();
		}
		//Add if Photo does not exists
		if(!containsPhoto(p)){
			photoList.add(new Photo(p.toLowerCase()));
		}
	}
	/**
	 * Add the given photo i to the Photo with the given name n in the database. If a Photo with the name n is not in the database throw a java.lang.IllegalArgumentException. If i is already in the list of photos associated with the Photo named n, just return.
	 * 
	 * @param p Name of Photo
	 * @param s Name of Slideshows
	 * */
	public void addSlideshows(String s, String p){
		if(p == null){
			throw new IllegalArgumentException();
		}
		List<String> photoList = getSlideshows(s);
		if(photoList == null){
			throw new IllegalArgumentException();
		}
		if(!hasSlideshow(s, p)){
			photoList.add(getPhotos(p).getName().toLowerCase());
		}
	}
	/**
	 * Return true iff Photo with the name p is in the database.
	 * 
	 * @param n Name of Photo
	 * @return true if found, false if not found
	 * */
	public boolean containsPhoto(String p){
		if(p == null){
			throw new IllegalArgumentException();
		}
		return getPhotos(p) != null;
	}
	/**
	 * Return true iff the photo i appears in at least one Photo's list of slideshows in the database.
	 * 
	 * @param i Name of photo
	 * @return true if found, false if not found
	 * */
	public boolean containsSlideshow(String s){
		if(s == null){
			throw new IllegalArgumentException();
		}
		Iterator<Photo> iter = photoList.iterator();
		while (iter.hasNext()){
			Photo cPhoto = iter.next();
			if(cPhoto.getSlideshows().contains(s.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	/**
	 * Returns true iff the given photo i is in the Photo with the given name n. If a Photo with the name n is not in the database, return false.
	 * 
	 * @param n Name of Photo
	 * @param i Name of photo
	 * @return true if found, false if not found
	 * */
	public boolean hasSlideshow(String s, String p){
		if(s == null || p == null){
			throw new IllegalArgumentException();
		}
		return getSlideshows(s).contains(s.toLowerCase());
	}
	/**
	 * Return the list of photos for the Photo with the given name s. If a Photo with the name s is not in the database, return null.
	 * 
	 * @param s Name of Photo
	 * @return list of photos, null if not found
	 * */
	public List<String> getSlideshows(String s){
		if(s == null){
			throw new IllegalArgumentException();
		}
		if(containsPhoto(s)){
			return getPhotos(s).getSlideshows();
		}
		return null;
	}
	
	/**
	 * Return an Iterator over the Photo objects in the database. The Photos should be returned in the order they were added to the database (resulting from the order in which they are in the text file).
	 * @return iterator of database that contains Photos
	 * */
	public Iterator<Photo> iterator(){
		return photoList.iterator();
	}
	/**
	 * Remove the Photo with the given name n from the database. If a Photo with the name n is not in the database, return false; otherwise (i.e., the removal is successful) return true.
	 * 
	 * @param n Name of Photo
	 * @return true if success, false if failed
	 * */
	public boolean removePhoto(String p){
		if(p == null){
			throw new IllegalArgumentException();
		}
		if(containsPhoto(p)){
			return photoList.remove(getPhotos(p));
		}
		return false;
	}
	/**
	 * Return the number of Photos in this database.
	 * 
	 * @return the size of the database
	 * */
	public int size(){
		return photoList.size();
	}
	/**
	 * Find and return the Photo, returns null if not found.
	 * 
	 * @param n Name of Photo
	 * @return Photo reference or null if not found.
	 * */
	private Photo getPhotos(String s){
		if(s== null || s.isEmpty()){
			return null;
		}
		Iterator<Photo> iter = photoList.iterator();
		while (iter.hasNext()){
			Photo cPhoto = iter.next();
			if(cPhoto.getName().equalsIgnoreCase(s)){
				return cPhoto;
			}
		}
		return null;
	}
}
