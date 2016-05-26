//
// Created by orenk on 5/25/16.
//

#pragma once

class CElement
{
public:
    CElement();
    CElement(int num);
    CElement(const CElement& other);
    ~CElement();

    int getNum() const;
    void setNum(int num);

private:
    int m_num;
};

