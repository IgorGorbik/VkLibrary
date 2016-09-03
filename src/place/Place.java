package place;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Игорь
 */
public class Place {

    private String countryId;
    private String cityId;

    private String country;
    private String city;

    private Place(String countryId, String cityId) {
        this.countryId = countryId;
        this.cityId = cityId;
    }

    /**
     * get Place by parameters
     *
     * @return Place
     */
    public static Place createPlace(String cityId1, String countryId1) throws IOException {
        Place place = new Place(cityId1, countryId1);
        if (!cityId1.isEmpty()) {
            String url1 = "https://api.vk.com/method/"
                    + "database.getCitiesById.xml"
                    + "?city_ids=" + cityId1;
            Document html1 = Jsoup.connect(url1).get();
            String tempCity = html1.getElementsByTag("name").text();
            place.setCity(tempCity);
        }
        if (!countryId1.isEmpty()) {
            String url1 = "https://api.vk.com/method/"
                    + "database.getCountriesById.xml"
                    + "?country_ids=" + countryId1;
            Document html1 = Jsoup.connect(url1).get();
            String tempCountry = html1.getElementsByTag("name").text();
            place.setCountry(tempCountry);
        }
        return place;
    }

    @Override
    public String toString() {
        if (!(cityId.equals("0") || countryId.equals("0"))) {
            return country + "," + city;
        } else {
            return "";
        }
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    private void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    private void setCity(String city) {
        this.city = city;
    }

}
