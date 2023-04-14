package bean;

import java.io.Serializable;

public class Info implements Serializable {
    private static final long serialVersionUID = 1L;
    private String dianming;
    private String mingzi;
    private double price;
    private Integer num;
    private  long shangpin;
    private  long id;
    private int flag=0;
    private double cost;
    public int isFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Info(long id, String dm, String mz, double price, Integer num, Long sp,int fg) {
        this.dianming = dm;
        this.mingzi = mz;
        this.price = price;
        this.shangpin = sp;
        this.num=num;
        this.id=id;
        this.flag=fg;
        this.cost=price*num;
    }

    public String getDianming() {
        return dianming;
    }

    public void setDianming(String dianming) {
        this.dianming = dianming;
    }

    public Info() {
    }

    public Info(String dianming, String mingzi, double jiage, Integer num,long shangpin,int flag) {
        this.dianming = dianming;
        this.mingzi = mingzi;
        this.price = jiage;
        this.shangpin = shangpin;
        this.num=num;
        this.flag=flag;
        this.cost=jiage*num;
    }
    public long getID(){return id;}

    public void setId(Long id){this.id=id;}

    public String getMingzi() {
        return mingzi;
    }

    public void setMingzi(String mingzi) {
        this.mingzi = mingzi;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double jiage) {
        this.price = jiage;
    }

    public Integer getNumber() {
        return num;
    }

    public void setNumber(Integer shuliang) {
        this.num = shuliang;
    }

    public long getShangpin() {
        return shangpin;
    }

    public void setShangpin(long shangpin) {
        this.shangpin = shangpin;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
