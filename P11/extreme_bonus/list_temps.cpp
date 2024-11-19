#include "Date.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <map>
#include <string>
#include <iomanip>

using Temp = double;
int main(int argc, char* argv[]){
    if (argc != 2) {
        std::cerr << "Usage: " << argv[0] << " <data file>\n";
        return 1;
    }
    std::string filename = argv[1];
    std::ifstream file(filename);
    if (!file){
        std::cerr << "Can't open input file " << filename << '\n';
        return 1;
    }
    std::map<Date, Temp> temps;
    std::string line;
    
    while (std::getline(file, line)){
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
    while (true){
        std::cout << "Enter start and end dates (YYYY/MM/DD YYYY/MM/DD), or 'q' to quit: ";
        Date start, end;
        if (!(std::cin >> start)){
            std::cout << "Invalid input or exiting program.\n";
            break;
        }
        if (temps.find(start) == temps.end()){
            std::cout << start << " is not in the database!\n";
            continue;
        }
        if (!(std::cin >> end)){
            std::cout << "Invalid input or exiting program.\n";
            break;
        }
        if (end < start){
            std::cout << end << " is earlier than " << start << "!\n";
            continue;
        }
        auto it = temps.lower_bound(start);
        auto stop = temps.upper_bound(end);
        std::cout << "Date       | Temperature (°F)\n";
        std::cout << "-----------|-------------------\n";
        bool found = false;
        while (it != stop) {
            std::cout << it->first << " | " << std::fixed << std::setprecision(1) << it->second << '\n';
            ++it;
            found = true;
        }
        if (!found) {
            std::cout << "No temperatures found for the given date range.\n";
        }
    }

    return 0;
}