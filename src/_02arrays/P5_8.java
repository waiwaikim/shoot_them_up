package _02arrays;

public class P5_8 {

    //However, years that are divisible by 100 (for example, 1900) are not leap years,
    // but years that are divisible by 400 are leap years (for example, 2000).

    public static boolean isLeapYear(int year){


        if (year%400==0) return true;
        if (year%100==0) return false;
        if (year%4==0) return true;

        return false;

    }
    public static void main(String[] args) {

        //Testing leap year
        int[] years = {
        1804,        1808,        1812,        1816,        1820,        1824,        1828,        1832,        1836,        1840,        1844,        1848,        1852,        1856,        1860,        1864,
        1868,        1872,        1876,        1880,        1884,        1888,        1892,        1896,        1904,        1908,        1912,        1916,        1920,        1924,        1928,        1932,
        1936,        1940,        1944,        1948,        1952,        1956,        1960,        1964,        1968,        1972,        1976,        1980,        1984,        1988,        1992,        1996,
        2000,        2004,        2008,        2012,        2016,        2020,        2024,        2028,        2032,        2036,        2040,        2044,        2048,        2052,        2056,        2060,
        2064,        2068,        2072,        2076,        2080,        2084,        2088,        2092,        2096,        2104,        2108,        2112,        2116,        2120,        2124,        2128,
        2132,        2136,        2140,        2144,        2148,        2152,        2156,        2160,        2164,        2168,        2172,        2176,        2180,        2184,        2188,        2192,
        2196,        2204,        2208,        2212,        2216,        2220,        2224,        2228,        2232,        2236,        2240,        2244,        2248,        2252,        2256,        2260,
        2264,        2268,        2272,        2276,        2280,        2284,        2288,        2292,        2296,        2304,        2308,        2312,        2316,        2320,        2324,        2328,
        2332,        2336,        2340,        2344,        2348,        2352,        2356,        2360,        2364,        2368,        2372,        2376,        2380,        2384,        2388,        2392,
        2396,        2400};

        boolean work = true;
        for(int year: years){

            if(isLeapYear(year) == false){
                work=false;
            }
        }

        if(work== false){System.out.println("Error!"); }
        else{ System.out.println("You got all leap years correct"); }

        //Testing non-leap year
        int[] not_years = { 1806, 1810, 1850, 1890, 2005, 2010, 2030};
        for(int year:not_years){
            System.out.println(isLeapYear(year));
        }



    }
}
