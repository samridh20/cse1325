#ifndef TIMER_H
#define TIMER_H

#include "clock.h"
#include "timer_expired.h"

class Timer : public Clock {
public:
    Timer(int hours, int minutes, int seconds) : Clock(hours, minutes, seconds) {}
    virtual ~Timer() {}

    void tic() override;   
};

#endif