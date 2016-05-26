/*
 * CFile.h
 *
 *  Created on: May 25, 2016
 *      Author: orenk
 */
#pragma once
#include <fstream>

template <class T>
class CFile
{
public:
    CFile(const char* path);
    virtual ~CFile();

    T read();
    int read(T** apBuf, int aNum);
    void write(const T& t);
    void write(const T *apBuf, int aNum);
    int size();

private:
    std::ifstream m_istream;
    std::ofstream m_ostream;
};

template<class T> CFile<T>::CFile(const char * path)
{
    m_istream.open(path, std::ios::in);
    m_ostream.open(path, std::ios::out);
}

template<class T> CFile<T>::~CFile()
{
    m_istream.close();
    m_ostream.close();
}

template<class T>
inline T CFile<T>::read()
{
    T t;
    m_istream.read((char*)&t, sizeof(t));
    return t;
}

template<class T>
inline int CFile<T>::read(T** apBuf, int aNum) {

    apBuf = new T*(aNum);
    m_istream.read((char*)apBuf, aNum * sizeof(apBuf));
    return m_istream.gcount() / sizeof(T);
}

template<class T>
inline void CFile<T>::write(const T& t)
{
    m_ostream.write((char*)&t, sizeof(t));
}

template<class T>
inline void CFile<T>::write(const T* apBuf, int aNum) {
    m_ostream.write((char*)&apBuf, aNum * sizeof(T));
}

template<class T>
inline int CFile<T>::size() {
    return 0;
}



