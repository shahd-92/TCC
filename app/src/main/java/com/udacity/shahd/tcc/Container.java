package com.udacity.shahd.tcc;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.concurrent.atomic.AtomicInteger;

public class Container implements Parcelable {
    public static final String FIELD_COUNT = "Container";

    private static AtomicInteger INTEGER_COUNTER = new AtomicInteger(0);

//    private int recipeId;

    private int id;
    private String containerName;
    private String lont;
    private String lat;
    private int status;
    private String region;


//    public int getId() {
//        return recipeId;
//    }
//
//    public String getIdString() {
//        return Integer.toString(recipeId);
//    }


    public String getContainerName() {
        return containerName;
    }

    public int getId() {
        return id;
    }

    public String getLont() {
        return lont;
    }

    public String getLat() {
        return lat;
    }
    public String getRegion() {
        return region;
    }
    public int getStatus() {
        return status;
    }

    public Container setId(int id) {
        this.id = id;
        return this;
    }
    public void setStatus(int status) {
        this.status = status;
    }


    public Container() {
    }

    public Container(int ID) {
        this.id = id;
    }

    public Container(String containerName, String lont,String lat,String region, int status) {
        this.lont = lont;
    }

    public Container(String containerName) {
        this.containerName = containerName;
    }

    public Container(int id, String lont, String lat, String region, String containerName, int status) {
        this.id = id;
        this.lont = lont;
        this.lat = lat;
        this.region = region;
        this.containerName = containerName;
        this.status = status;
    }

    public Container(Parcel in) {
        this.id = in.readInt();
        this.lont = in.readString();
        this.lat = in.readString();
        this.region = in.readString();
        this.containerName = in.readString();
        this.status = in.readInt();
    }



    public static final Parcelable.Creator<Container> CREATOR
            = new Parcelable.Creator<Container>() {
        public Container createFromParcel(Parcel in) {
            return new Container(in);
        }

        public Container[] newArray(int size) {
            return new Container[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
//        if (!toolName.isEmpty())
        dest.writeString(lont);
//        if (!description.isEmpty())
        dest.writeString(lat);
//        if (!description.isEmpty())
        dest.writeString(region);
//        if (!description.isEmpty())
        dest.writeString(containerName);
        dest.writeInt(status);
//        }
    }



//
//    //  create() & delete() needs to be called inside a transaction.
//    static void create(Realm realm, boolean b) {
//        create(realm, false);
//    }

//    static void create(Realm realm, boolean randomlyInsert) {
//        Parent parent = realm.where(Parent.class).findFirst();
////        RealmList<Recipe> recipes = parent.getRecipeList();
//        RealmList<Recipe> recipes = new RealmList<Recipe>();
//        Recipe recipe = realm.createObject(Recipe.class, increment());
//        if (randomlyInsert && recipes.size() > 0) {
//            Random rand = new Random();
//            recipes.listIterator(rand.nextInt(recipes.size())).add(recipe);
//        } else {
//            recipes.add(recipe);
//        }
//    }
//
//    static void delete(Realm realm, long id) {
//        Recipe recipe = realm.where(Recipe.class).equalTo(FIELD_COUNT, id).findFirst();
//        // Otherwise it has been deleted already.
//        if (recipe != null) {
//            recipe.deleteFromRealm();
//        }
//    }

}