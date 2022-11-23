package bj.orace.voyage.services;

public class FoodItem {
    public int id;
    public String nameofthefood;
    public String price;
    public String stocks;
    public String imageurl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameofthefood() {
        return nameofthefood;
    }

    public void setNameofthefood(String nameofthefood) {
        this.nameofthefood = nameofthefood;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
