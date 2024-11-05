#include "timer.h"
#include "timer_expired.h"
#include <iostream>
#include <string>
#include <cstdlib>

int main(int argc, char* argv[]) {
    if (argc != 4) {
        std::cerr << "usage: timer <hour> <minutes> <seconds>" << std::endl;
        return -1;
    }

    try {
        int hours = std::stoi(argv[1]);
        int minutes = std::stoi(argv[2]);
        int seconds = std::stoi(argv[3]);

        Timer timer(hours, minutes, seconds);

        while (true) {
            timer.print();
            std::string input;
            std::getline(std::cin, input);

            if (input == "q") break;

            timer.tic();
        }
    } catch (const Timer_expired& e) {
        std::cout << e.what() << std::endl;
    }

    return 0;
}