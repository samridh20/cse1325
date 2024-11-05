#include "clock.h"
#include <iostream>
#include <string>
#include <cstdlib>

int main(int argc, char* argv[]){
    if (argc != 4){
        std::cerr << "usage:clock <hour> <minutes> <seconds>" << std::endl;
        return -1;

    }

    try{
        int hours = std::stoi(argv[1]);
        int minutes = std::stoi(argv[2]);
        int seconds = std::stoi(argv[3]);
        Clock clock(hours, minutes, seconds);

        while (true){

            clock.print();
            std::string input;
            std::getline(std::cin, input);


            if (input == "q") break;

            clock.tic();
        }
    } 
    catch (const std::out_of_range& e){

        std::cerr << e.what() << std::endl;
        return -2;
    } 
    catch (const std::invalid_argument& e){

        std::cerr << "Invalid input for hour, minutes, or seconds" << std::endl;
        return -1;
    }
    return 0;
}