#ifndef CLOCK_H
#define CLOCK_H

#include <iostream>
#include <stdexcept>
#include <iomanip>

class Clock {
public:
    Clock(int hours, int minutes, int seconds);
    virtual ~Clock() {}
    void tic();
    void print() const;

private:
    int _hours;
    int _minutes;
    int _seconds;
};

#endif