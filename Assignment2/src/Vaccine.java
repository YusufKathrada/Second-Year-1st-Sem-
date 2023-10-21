// Yusuf Kathrada
// KTHYUS001
// Vaccine

public class Vaccine implements Comparable<Vaccine> 
{

    String country;
    String date;
    String Vaccinations;

    public Vaccine() {}

    public Vaccine(String Line) {
        // ie of line = South Africa, 2020-02-01, 5432
        String[] Info = Line.split(",");
        country = Info[0];
        date = Info[1];

        //this.count();
        if (Info.length == 3) {
            Vaccinations = Info[2];

        } else {

            Vaccinations = "0";
        }

    }

    public int compareTo(Vaccine v) {
        return (country + date).compareTo(v.country + v.date);
    }

   
    public String toString() {
        return country + "," + date + "," + Vaccinations;
    }
}
