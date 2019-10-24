package _03objects.P8_14;

import java.util.Comparator;

class Country {

    private String name;
    private int population;
    private int area;

    public Country(){

    }

    public Country(String str, int pop, int a){
        name=str;
        population=pop;
        area = a;
    }
    public String getName(){
        return this.name;
    }
    public int getPopulation(){
        return this.population;
    }
    public int getArea(){
        return this.area;
    }

    public void setName(String s){
        name = s;
    }
    public void setPopulation(int n){
        population = n;
    }
    public void setArea(int a){
        area = a;
    }

    public String toString(){
        return  "name: " + this.name + "population: " + this.population + "area: " + this.area;

    }
}

class Sortbypop implements Comparator<Country>{
    @Override
    public int compare(Country o1, Country o2) {
        return o2.getPopulation() - o1.getPopulation();
    }
}

class Sortbyarea implements Comparator<Country>{
    @Override
    public int compare(Country o1, Country o2) {
        return o2.getArea() - o1.getArea();
    }
}

class Sortbydensity implements Comparator<Country>{
    @Override
    public int compare(Country o1, Country o2) {
        return (o2.getPopulation()/o2.getArea()) - (o1.getPopulation()/o1.getArea());
    }
}