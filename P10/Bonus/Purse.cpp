#include "Purse.h"


Purse::Purse(int pounds, int shillings, int pence)
    : _pounds(pounds), _shillings(shillings), _pence(pence) {
    rationalize();
}

void Purse::rationalize() {
    if (_pence >= 12) {
        _shillings += _pence / 12;
        _pence %= 12;
    }
    if (_shillings >= 20) {
        _pounds += _shillings / 20;
        _shillings %= 20;
    }
}

std::ostream& operator<<(std::ostream& os, const Purse& purse) {                                // Output stream operator
    os << "£" << purse._pounds << " " << purse._shillings << "s" << purse._pence << "d";
    return os;
}

std::istream& operator>>(std::istream& is, Purse& purse) {                                      // Input stream operator
    char poundSymbol; 
    is >> poundSymbol >> purse._pounds >> purse._shillings >> poundSymbol >> purse._pence >> poundSymbol;
    purse.rationalize();
    return is;
}


Purse& Purse::operator++() {
    _pence++;
    rationalize();
    return *this;
}
Purse Purse::operator++(int) {
    Purse temp = *this;
    ++(*this);
    return temp;
}
Purse Purse::operator+(const Purse& other) const {
    return Purse(_pounds + other._pounds, _shillings + other._shillings, _pence + other._pence);
}
Purse Purse::operator-(const Purse& other) const {
    return Purse(_pounds - other._pounds, _shillings - other._shillings, _pence - other._pence);
}
Purse& Purse::operator+=(const Purse& other) {
    _pounds += other._pounds;
    _shillings += other._shillings;
    _pence += other._pence;
    rationalize();
    return *this;
}
Purse& Purse::operator-=(const Purse& other) {
    _pounds -= other._pounds;
    _shillings -= other._shillings;
    _pence -= other._pence;
    rationalize();
    return *this;
}