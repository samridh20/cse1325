#include "Date.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <string>
#include <iomanip>

using Temp = double;

int main(int argc, char* argv[]){
    if (argc != 2){

        std::cerr << "Usage: " << argv[0] << " <data file>\n";
        return 1;
    }

    std::string filename = argv[1];
    std::ifstream file(filename);
    if (!file){
        
        std::cerr << "Can't open input file " << filename << '\n';
        return 1;
    }
    std::map<Date, Temp>temps;
    std::string line;
    
    while (std::getline(file,line)){

        std::istringstream ss(line);
        std::string continent, country, state, region, month, day, year, temperature;
        std::getline(ss, continent, ',');
        std::getline(ss, country, ',');
        std::getline(ss, state, ',');
        std::getline(ss, region, ',');
        std::getline(ss, month, ',');
        std::getline(ss, day, ',');
        std::getline(ss, year, ',');
        std::getline(ss, temperature, ',');

        int m = std::stoi(month);
        int d = std::stoi(day);
        int y = std::stoi(year);
        double temp = std::stod(temperature);
        Date date(y, m, d);
        temps[date] = temp;
    }

    while (true) {
        std::cout << "Starting date to list (year month day): ";
        int sy, sm, sd;
        if (!(std::cin >> sy >> sm >> sd)) break;
        Date start(sy, sm, sd);
        std::cout << "Ending date to list (year month day): ";
        int ey, em, ed;
        if (!(std::cin >> ey >> em >> ed)) break;
        Date end(ey, em, ed);

        auto it = temps.lower_bound(start);
        std::cout << "Date       | Temperature (\u00B0F)\n";
        std::cout << "-----------|-------------------\n";

        while (it != temps.end() && it->first <= end){
            std::cout << it->first << " | " << std::fixed << std::setprecision(1) << it->second << '\n';
            ++it;
        }
    }

    return 0;
}
