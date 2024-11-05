#ifndef TIMER_EXPIRED_H
#define TIMER_EXPIRED_H

#include <stdexcept>
#include <string>

class Timer_expired : public std::runtime_error {
public:
    Timer_expired();  
};

#endif