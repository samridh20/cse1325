#ifndef PURSE_H
#define PURSE_H

#include <iostream>

class Purse {
public:
   
    Purse(int pounds = 0, int shillings =0, int pence = 0);
    friend std::ostream& operator<<(std::ostream& os, const Purse& purse);
    auto operator<=>(const Purse&) const = default;

    Purse& operator++();
    Purse operator++(int); 
    Purse operator+(const Purse& other) const;
    Purse operator-(const Purse& other) const;
    Purse& operator+=(const Purse& other);
    Purse& operator-=(const Purse& other);

private:
    int _pounds;
    int _shillings;
    int _pence;
    void rationalize();
};
#endif 