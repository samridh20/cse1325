#include "Purse.h"


Purse::Purse(int pounds, int shillings, int pence)                 // Constructor with initialization list and rationalization
    : _pounds(pounds), _shillings(shillings), _pence(pence) {
    rationalize();
}
void Purse::rationalize(){
    if (_pence >= 12) {
        _shillings += _pence / 12;
        _pence %= 12;
    }
    if (_shillings >= 20){
        _pounds += _shillings / 20;
        _shillings %= 20;
    }
}

std::ostream& operator<<(std::ostream& os, const Purse& purse){      // Output stream operator
    os << "£" << purse._pounds << " " << purse._shillings << "s" << purse._pence << "d";
    return os;
}
Purse& Purse::operator++() {                                         // Pre-increment operator
    _pence++;
    rationalize();
    return *this;
}
Purse Purse::operator++(int) {                                      // Post-increment operator
    Purse temp = *this;
    ++(*this);
    return temp;
}
Purse Purse::operator+(const Purse& other) const{                // Addition operator
    return Purse(_pounds + other._pounds, _shillings + other._shillings, _pence + other._pence);
}
Purse Purse::operator-(const Purse& other) const {                  // Subtraction operator
    return Purse(_pounds - other._pounds, _shillings - other._shillings, _pence - other._pence);
}
Purse& Purse::operator+=(const Purse& other) {                     // Compound addition operator
    _pounds += other._pounds;
    _shillings += other._shillings;
    _pence += other._pence;
    rationalize();
    return *this;
}
Purse& Purse::operator-=(const Purse& other) {                  // Compound subtraction operator
    _pounds -= other._pounds;
    _shillings -= other._shillings;
    _pence -= other._pence;
    rationalize();
    return *this;
}