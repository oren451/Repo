################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../Animal.cpp \
../Birds.cpp \
../Fish.cpp \
../Flamingo.cpp \
../GoldFish.cpp \
../Horses.cpp \
../Mammals.cpp \
../Mermaid.cpp \
../Zoo.cpp \
../main.cpp 

OBJS += \
./Animal.o \
./Birds.o \
./Fish.o \
./Flamingo.o \
./GoldFish.o \
./Horses.o \
./Mammals.o \
./Mermaid.o \
./Zoo.o \
./main.o 

CPP_DEPS += \
./Animal.d \
./Birds.d \
./Fish.d \
./Flamingo.d \
./GoldFish.d \
./Horses.d \
./Mammals.d \
./Mermaid.d \
./Zoo.d \
./main.d 


# Each subdirectory must supply rules for building sources it contributes
%.o: ../%.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"$(@:%.o=%.d)" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


