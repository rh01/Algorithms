/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class WeatherDataSt implements Subject {

    private double mTemperature;
    private double mHumidity;
    private double Pressure;


    public WeatherDataSt(double mTemperature, double mHumidity, double pressure) {
        this.mTemperature = mTemperature;
        this.mHumidity = mHumidity;
        Pressure = pressure;
    }

    public double getmTemperature() {
        return mTemperature;
    }

    public void setmTemperature(double mTemperature) {
        this.mTemperature = mTemperature;
    }

    public double getmHumidity() {
        return mHumidity;
    }

    public void setmHumidity(double mHumidity) {
        this.mHumidity = mHumidity;
    }

    public double getPressure() {
        return Pressure;
    }

    public void setPressure(double pressure) {
        Pressure = pressure;
    }


    public static void main(String[] args) {

    }

    @Override
    public void registerObserver() {

    }

    @Override
    public void removeObserver() {

    }

    @Override
    public void notifyObserver() {

    }
}
