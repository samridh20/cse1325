#ifndef DATE_H
#define DATE_H

#include <iostream>
#include <iomanip>
#include <string>
#include <sstream>

class Date{
public:
    Date(int year = 1970, int month = 1, int day = 1) : _year(year), _month(month), _day(day){}
    auto operator<=>(const Date&) const = default;
    
    friend std::ostream& operator<<(std::ostream& os, const Date& date){
        char old_fill = os.fill();

        os << date._year << '/'
           << std::setw(2) << std::setfill('0') << date._month << '/'
           << std::setw(2) << date._day;
        os.fill(old_fill);
        return os;
    }

    friend std::istream& operator>>(std::istream& is, Date& date){
        std::string input;
        if (!(is >> input)) return is; 
        std::istringstream ss(input);
        char sep1, sep2;
        int year, month, day;
        if ((ss >> year >> sep1 >> month >> sep2 >> day) && sep1 == '/' && sep2 == '/') {
            date._year = year;
            date._month = month;
            date._day = day;
        } else {
            is.setstate(std::ios::failbit); 
        }
        return is;
    }
private:
    int _year, _month, _day;
};

#endif