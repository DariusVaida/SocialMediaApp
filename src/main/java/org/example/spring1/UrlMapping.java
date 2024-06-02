package org.example.spring1;

public class UrlMapping {
    public static final String API = "/api";

    public static final String POSTS = "/posts";

    public static final String AUTH = API + "/auth";

    public static final String USERS = API + "/users";

    public static final String LIKE = "/like";
    public static final String LIKED = "/liked";


    public static final String LIKES_USER = "/likes/user";
    public static final String CREATE = "/create";
    public static final String DELETE_POST = "/delete";


    public static final String UPLOAD_FILE = "/uploadFile";
    public static final String PHOTO = "/photo";

    public static final String ID_PART = "/{id}";
    public static final String CHANGE_NAME_PART = "/change-name";

    public static final String SIGN_IN = "/sign-in";
    public static final String SIGN_UP = "/sign-up";
}
