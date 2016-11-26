package we.are.en3.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import we.are.en3.client.MyClimateService;
import we.are.en3.client.model.DataPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * This class extends a RemoteServiceServlet that implements the MyClimateService interface.
 * It is the server side service point for RPC requests from the client side.
 * It offers services in connection with the DataStore.
 *
 * @author Team SE_EN3, University of Zurich
 * @version 0.02
 *
 */
public class MyClimateServiceImpl extends RemoteServiceServlet implements MyClimateService {


    /**
     * Method returns for a given area the lowest and highest year.
     * This method is called from method fetchTable() in class TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous?
     */
    @Override
    public ArrayList<String> getMinMaxYear(String area) {

        //This list will be returned
        ArrayList<String> returnList = new ArrayList<String>();
        String minYear = new String();
        String maxYear = new String();

        //Todo: remove meta from all methods in classes and interfaces
        String meta="city";

        //extracts first and last year in DataStore for chosen area
        if (meta.equals("city")){
            ArrayList<DataPoint> resList = DataStore.getInstance().areaMap.get(area);
            minYear=resList.get(0).getDate();
            maxYear=resList.get(resList.size()-1).getDate();
        }else {
            ArrayList<DataPoint> resList = DataStore.getInstance().areaMap.get(area);
            //Todo
        }

        //add first and last year to the data structure and return
        returnList.add(minYear);
        returnList.add(maxYear);
        return returnList;
    }


    /**
     * Method returns the number of DataPoints stored in DataStore's areaMap
     * dependent on the values Area, StartDate, EndDate
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public Integer getResultsCount(String area, String dateFrom, String dateTo) {

        //return variable
        Integer size_;

        //indexes to be determined and used to calculate the number of DataPoints (=size_)
        Integer firstIndex=0;
        Integer lastIndex=0;

        //returns for an area: the size of the array, i.e. the number of data points
            ArrayList<DataPoint> areaArray = DataStore.getInstance().areaMap.get(area);

            //find first index
            for (DataPoint dp : areaArray) {

                //find year and exit loop
                //dp.getDate() has format xxxx-xx-xx
                Boolean found = dateFrom.equals(dp.getDate().split("-")[0]);
                if (found) {

                    //Take whatever you found first and exit loop. (January if it exists)
                    firstIndex = areaArray.indexOf(dp);
                    break;
                }
            }

            //find last index
            for (DataPoint dp : areaArray) {

                //find year and continue looping
                //dp.getDate() has format xxxx-xx-xx
                Boolean found = dateTo.equals(dp.getDate().split("-")[0]);
                if (found) {

                    //Take whatever you found last. (December if it exists)
                    lastIndex = areaArray.indexOf(dp);
                }
            }

            //size=1 if lastIndex=firstIndex
            size_ = lastIndex-firstIndex+1;



        //return number of data points
        return size_;
    }

    /**
     * Method returns an ArrayList of DataPoints stored in DataStore's areaMap
     * It is called from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     * ToDo: String meta is superfluous?
     */
    @SuppressWarnings("Duplicates")
    @Override
    public ArrayList<DataPoint> getResults(String area, String dateFrom, String dateTo,
                                           Integer seqStart, Integer seqEnd) {

        //Of type list since sublist() call below returns a List
        ArrayList<DataPoint> returnArrayList = null;

        //exception handling: if area is null
        if (area==null) return null;

        //Read out the data store


            //indexes to be determined and used to provide the requested DataPoints
            Integer firstIndex=0;
            Integer lastIndex=0;

            //returns for an area: the size of the array, i.e. the number of data points
                ArrayList<DataPoint> areaArray = DataStore.getInstance().areaMap.get(area);

                //find first index
                for (DataPoint dp : areaArray) {

                    //find year and exit loop
                    //dp.getDate() has format xxxx-xx-xx
                    Boolean found = dateFrom.equals(dp.getDate().split("-")[0]);
                    if (found) {

                        //Take whatever you found first and exit loop. (January if it exists)
                        firstIndex = areaArray.indexOf(dp);
                        break;
                    }
                }

                //find last index
                for (DataPoint dp : areaArray) {

                    //find year and continue looping
                    //dp.getDate() has format xxxx-xx-xx
                    Boolean found = dateTo.equals(dp.getDate().split("-")[0]);
                    if (found) {

                        //Take whatever you found last. (December if it exists)
                        lastIndex = areaArray.indexOf(dp);
                    }
                }

                //Extract requested sub list, x=[a,b,c,d,e] x.sublist(1,4)->b,c,d, without last!
                List<DataPoint> resList = areaArray.subList(firstIndex, lastIndex+1);

                //seqEnd is already 1 higher (+1 not necessary)
                List<DataPoint> returnList = resList.subList(seqStart, seqEnd);

                //cast to ArrayList
                returnArrayList = new ArrayList<DataPoint>(returnList);




        //Cast List to ArrayList of DataPoints
        return returnArrayList;
    }

    /**
     * This method returns the sorted list of countries taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getCountryList() {

        return DataStore.getInstance().countrySortedList;
    }

    /**
     * This method returns the sorted list of cities taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getCityList() {
        return DataStore.getInstance().citySortedList;
    }

    /**
     * This method returns the sorted list of areas (cities+countries) taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getAreaList() {

        //populates cities
        ArrayList<String> areas = new ArrayList<String>(getCityList());

        //populates countries
        areas.addAll(getCountryList());

        //ToDo: no longer needed since it remains unsorted
        //for(String key: DataStore.getInstance().areaMap.keySet()) {
        //    cities.add(key);
        //}

        return areas;
    }

    /**
     * This method returns the list of dates taken from the DataStore
     * It is used to initialize the dropdown list of the TextBoxes.
     * It is called with RpcService from TablePresenter
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<String> getDatesList(String area) {
        //
        return DataStore.getInstance().areaToYearMap.get(area);
    }

    /**
     * This method returns a list of cities and average temperatures taken from the DataStore
     * for the requested year
     *
     *
     * @pre
     * @post
     * @param
     * @return
     */
    @Override
    public ArrayList<ArrayList<String>> getCitiesAverageTemperatureList(String year) {

        //return data structure
        ArrayList<ArrayList <String>> citiesAverageTemperatureList = new ArrayList<ArrayList<String>>();

        //cities in a list: used to iterate through
        ArrayList<String> citiesList = DataStore.getInstance().citySortedList;

        //indexes to be determined and used to provide the requested DataPoints
        Integer firstIndex=0;

        //iterate through all cities to fill the data structure
        for (String city : citiesList) {

            // new inner array for each city, otherwise this grows for each city
            ArrayList <String> cityAverageTemperatureList = new ArrayList<String>();

            //returns an array of data points (one element for each date)
            ArrayList<DataPoint> areaArray = DataStore.getInstance().areaMap.get(city);

            //used to calculate average yearly temperature
            double averageYearlyTemperature = 0;
            int counter=0;

            //used to leave loop early
            Boolean wasHere = false;

            //iterate through all data points of a city and calculate the yearly average of the requested year
            for(DataPoint dp : areaArray){

                //find all data points of a year
                //dp.getDate() has format xxxx-xx-xx
                Boolean found = year.equals(dp.getDate().split("-")[0]);

                if (found) {

                    wasHere=true;
                    averageYearlyTemperature+=dp.getAverageTemperature();
                    counter++;

                } else {

                    //leave loop
                    if (wasHere==true){
                        break;
                    }

                }


            }

            //fill the inner data structure
            cityAverageTemperatureList.add(city);
            averageYearlyTemperature=averageYearlyTemperature/counter;
            String averageYearlyTemperatureString = String.format("%.2f°C",averageYearlyTemperature);
            cityAverageTemperatureList.add(averageYearlyTemperatureString);

            //fill the outer data structure
            citiesAverageTemperatureList.add(cityAverageTemperatureList);

        }

        return citiesAverageTemperatureList;

    }




}