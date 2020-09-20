package i5.las2peer.services.getWeatherService;

import java.util.List;

public class JsonResult {

	//private Coordinations coord;
	private List<Weather> weather;
	private String base;
	private Main main;
	private int visibility;
	//private Wind wind;
	//private Clouds clouds;
	private long dt;
	//private Sys sys;
	private int id;
	private String name;
	private int cod;


	public List<Weather> getWeather() {
                return weather;
        }

        public void setWeather(List<Weather> weather) {
                this.weather = weather;
        }

	public String getBase() {
                return base;
        }

        public void setBase(String base) {
                this.base = base;
        }

	public Main getMain() {
                return main;
        }

        public void setMain(Main main) {
                this.main = main;
        }

	public int getVisibility() {
                return visibility;
        }

        public void setVisibility(int visibility) {
                this.visibility = visibility;
        }

	public Long getDt() {
                return dt;
        }

        public void setDt(long dt) {
                this.dt = dt;
        }

	public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

	public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

	public int getCod() {
                return cod;
        }

        public void setCod(int cod) {
                this.cod = cod;
        }
}