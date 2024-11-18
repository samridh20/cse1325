#ifndef DATE_H
#define DATE_H

#include <iostream>
#include <iomanip>

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
private:
    int _year, _month, _day;
};

#endif