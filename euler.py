#coding=utf-8
import math
import re

#print("中文显示")

#Problem 1

def get_sum_of_mltp(n, a, b):
    sum = 0
    if a!=0 and b!=0:
        for x in range(n):
            if x%a==0 or x%b==0:
                sum += x
    return sum

#print(get_sum_of_mltp(1000,17,53))

#problem 2: 求斐波那契数列中的偶数项之和，给定上限

def get_sum_of_even_fibo_num(maxa):
    sum = 0
    count = 1
    while get_fibo(count) < maxa:
        tmpn = get_fibo(count)
        if tmpn%2==0:
            sum += tmpn
        count += 1
    return sum

def get_fibo(n):
    fibonum = 0
    if n<1:
        print("n must lager than 1")
        return
    if n==1:
        fibonum = 1
    if n==2:
        fibonum = 2
    if n > 2:
        fibonum =  get_fibo(n-2) + get_fibo(n-1)
    return fibonum

#print(get_sum_of_even_fibo_num(100))

#x = math.ceil(math.sqrt(600851475143))
#print(x)

#print(math.pow(2,3))

#tmp_no = 51
#print(3*17)

#Problem 03:

def is_prime(n):
    sqr = int(math.ceil(math.sqrt(n)))+1
    if n>2:
        for x in range(2,sqr):
            if  n%x==0:
                #print(str(n)+ " is NOT a prime!")
                return  "NOT"      
    #print(str(n)+ " is a prime!")
    return n

def get_prime_list(n):
    prime_list=[]
    for x in range(2,n+1):
        if isinstance(is_prime(x), int):
            prime_list.append(x)
    return prime_list
          
#print('---------')
#print(get_prime_list(37))

def get_prime_factor(nmb):
    factor_list = []
    tmpn = nmb
    if isinstance(is_prime(nmb), int):
        factor_list.append(nmb)
    else:        
        for x in range(tmpn,1,-1):
            if isinstance(is_prime(x),int) :
                while tmpn%x==0:
                    factor_list.append(x)
                    tmpn = tmpn/x
    return factor_list

def get_max_prime_factor(n):
    tmpn = n
    if isinstance(is_prime(tmpn), int):
        return tmpn
    for x in range(2,tmpn):
        if isinstance(is_prime(x),int) :
             while tmpn%x==0:
                tmpn = tmpn/x
                if isinstance(is_prime(int(tmpn)), int):
                    return int(tmpn)

#print(get_prime_factor(4856))
#print(max(get_prime_factor(4856)))
#print(get_max_prime_factor(12736352))


#problem 04
'''
A palindromic number reads the same both ways.
The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
Find the largest palindrome made from the product of two 3-digit numbers.
'''
#print(999*999)

def get_max_digit(n):
    max_digit_n=0
    if n<1:
        print("n must > 0 ")
        return "ERROR!"
    while n-1>=0:
        max_digit_n += 9*math.pow(10, n-1)
        n = n-1
    #print(max_digit_n)
    return int(max_digit_n)

def  get_max_palind_by_two_n_digit(n):
    tmpmax=0
    #prdct = set()
    tmpx = get_max_digit(n)
    #print(tmpx)
    for x in range(tmpx,1,-1):
        for y in range(tmpx,1,-1):
            tmpa = x*y
            tmpb = int("".join(reversed(list(str(tmpa)))))
            if tmpa == tmpb and tmpa > tmpmax :
                tmpmax = tmpa                
            #prdct.add(x*y)
    #print(tmpmax)
    return tmpmax

#print(get_max_palind_by_two_n_digit(2))
#print(sorted(prdct, reverse=True))

# problem 05
'''
2520 is the smallest number that can be divided by each of the numbers
from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible
by all of the numbers from 1 to 20?
'''
#print(get_prime_factor(2520))
#print("-----------")


def get_list_prmfct_to(n):
    list_prmfct = []
    for x in range(2,n+1):
        list_prmfct.append(get_prime_factor(x))
    #print(len(list_prmfct))
    rst = list_prmfct
    for li in list_prmfct:
        print(li)
    return rst

'''
def is_sub_list(a,b):
    tst = []
    print(a)
    print(len(a))
    print(b)
    print(len(b))
    if len(a)<=len(b):
        for t in a:
            if t in b:
                tst.append(t)
        if a == tst:
            print("a small or equal  than b!")
        elif:
            print(" a NOT equal b!")
        

is_sub_list(get_prime_factor(10),get_prime_factor(20))
'''

#print(get_list_prmfct_to(10))

def get_lcm(a,b):
    alst = get_prime_factor(a)
    blst = get_prime_factor(b)
    rint=1
    for i in alst:
        if i in blst:
            blst.remove(i)
    tmplist = alst+blst
    for i in tmplist:
        rint = rint*i
    return rint        

def get_gcd(a,b):
    return int(a*b/get_lcm(a,b))

#print(get_lcm(100,24))
#print(get_gcd(100,24))

#another way, is fast enough!
def get_lcm_to(n):
    rint = 1
    tmplist = get_prime_factor(2)
    for x in range(3,n+1):
        alist = get_prime_factor(x)
        for a in alist:
            if a in tmplist:
                tmplist.remove(a)
        tmplist += alist
        #print(tmplist)
    for i in tmplist:
        rint = rint*i
    return rint

#print(get_lcm_to(10))
           
#print("-----------------------")

#problem 06
'''
The sum of the squares of the first ten natural numbers is,
1^2 + 2^2 + ... + 10^2 = 385
The square of the sum of the first ten natural numbers is,
(1 + 2 + ... + 10)^2 = 552 = 3025
Hence the difference between the sum of the squares of the first ten natural numbers
and the square of the sum is 3025 − 385 = 2640.
Find the difference between the sum of the squares of the first one hundred natural
numbers and the square of the sum.
'''
def get_sqrsum(n):
    sqr = 0
    for x in range(1,n+1):
        sqr += math.pow(x,2)
    return int(sqr)

def get_sumsqr(n):
    sum = 0
    for x in range(1,n+1):
        sum += x
    return  int(math.pow(sum,2))

#print(get_sumsqr(10))
#print(get_sqrsum(10))
#print(get_sumsqr(100)-get_sqrsum(100))
#print("--------")

#problem 07
'''
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
we can see that the 6th prime is 13.
What is the 10 001st prime number?
'''

def get_nth_prime(n):
    count = 1
    fpm = 2
    if n == 1:
        return fpm
    while count<n:
        fpm += 1
        if isinstance(is_prime(fpm),int):
            count += 1
    return fpm

#print(get_nth_prime(6))
#print(get_prime_list(101)) 
#print('---------------')

#problem 08
'''
The four adjacent digits in the 1000-digit number that have the greatest product are
9 × 9 × 8 × 9 = 5832.
73167176531330624919225119674426574742355349194934.......71636269561882670428252483600823257530420752963450
Find the thirteen adjacent digits in the 1000-digit number that have the greatest product.
What is the value of this product?
'''
str1k = '7316717653133062491922511967442657474235534919493496983520312774506326239578318016984801869\
47885184385861560789112949495459501737958331952853208805511125406987471585238630507156932909632952\
27443043557668966489504452445231617318564030987111217223831136222989342338030813533627661428280644\
44866452387493035890729629049156044077239071381051585930796086670172427121883998797908792274921901\
69972088809377665727333001053367881220235421809751254540594752243525849077116705560136048395864467\
06324415722155397536978179778461740649551492908625693219784686224828397224137565705605749026140797\
29686524145351004748216637048440319989000889524345065854122758866688116427171479924442928230863465\
67481391912316282458617866458359124566529476545682848912883142607690042242190226710556263211111093\
70544217506941658960408071984038509624554443629812309878799272442849091888458015616609791913387549\
92005240636899125607176060588611646710940507754100225698315520005593572972571636269561882670428252\
483600823257530420752963450'

def get_max_product_adjacent(n):
    rlen = len(str1k)
    rmlt = 1
    print(rlen)
    while rlen>=n:
        tmprslt = int(str1k[rlen-1])*int(str1k[rlen-2])*int(str1k[rlen-3])*int(str1k[rlen-4])*int(str1k[rlen-5])*int(str1k[rlen-6])*int(str1k[rlen-7])*int(str1k[rlen-8])*int(str1k[rlen-9])*int(str1k[rlen-10])*int(str1k[rlen-11])*int(str1k[rlen-12])*int(str1k[rlen-13])
        if tmprslt > rmlt:
            rmlt = tmprslt
        rlen = rlen -1
    print(rmlt)

#get_max_product_adjacent(13)

# Problem 09
'''
A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
a^2 + b^2 = c^2
For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
'''
def gg_num_in(num,ct=0):
    wqpfs = []
    sqrws = []
    for x in range(2,num):
        if len(str(math.sqrt(x)))<10:
            sqrws.append(int(math.sqrt(x)))
            wqpfs.append(x)
    #print(len(wqpfs))
    #print(wqpfs)
    #print(sqrws)
    for n in wqpfs:
        i = wqpfs.index(n)
        for j in range(i+1,len(wqpfs)):
            tmpsum = n + wqpfs[j]
            a = int(math.sqrt(n))
            b = int(math.sqrt(wqpfs[j]))
            #print(tmpsum)
            if tmpsum in wqpfs and ct==0:
                print(str(n) + " + " + str(wqpfs[j]) + " = " + str(tmpsum))
            if tmpsum in wqpfs and ct!=0: 
                c = int(math.sqrt(tmpsum))
                dt = a+b+c
                if dt==ct:
                    print(str(n) + " + " + str(wqpfs[j]) + " = " + str(tmpsum))
                    print(a)
                    print(b)
                    print(c)
                    print(a*b*c)
                
#gg_num_in(1000,30)


# Problem 10
'''
The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
Find the sum of all the primes below two million.
'''
#print(get_prime_list(100))

# the solution below is a littel slow, try another way.
def get_prime_sum_below(n):
    psum = 0
    pl = get_prime_list(n)
    for i in pl:
        psum += i
    return psum

# another way
def get_prime_sum_2_below(n):
    psum = 0
    for x in range(2,n+1):
        if isinstance(is_prime(x), int) and x<n:
            psum += x
    return psum

#print(get_prime_sum_below(100))
#print(get_prime_sum_2_below(100))

# Problem 11
'''
What is the greatest product of four adjacent numbers in the same direction
(up, down, left, right, or diagonally) in the 20×20 grid?
'''

strix = "08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08\
49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00\
81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65\
52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91\
22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80\
24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50\
32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70\
67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21\
24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72\
21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95\
78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92\
16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57\
86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58\
19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40\
04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66\
88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69\
04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36\
20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16\
20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54\
01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48"

def get_int_list_from(strlst):
    intlst = []
    for s in strlst:
        intlst.append(int(s))
    return intlst

listrix = re.findall(r'.{59}',strix)
thelist = []
for item in listrix:
    itemlst = item.split(" ")
    intlst = get_int_list_from(itemlst)
    thelist.append(intlst)

'''
print("---------------")
for tl in thelist:
    print(tl)
print("---------------")
'''

#按横竖斜一并处理，貌似可以了。经验证，结果是OK的。只是具体逻辑还需要再细细回顾思考。
def get_prctmax_by_zhixian(lst):
    tmpmax = 0
    j = 0
    ln = len(lst)
    while j<ln:
        for i in range(ln):
            if i<17:
                a = lst[j][i]*lst[j][i+1]*lst[j][i+2]*lst[j][i+3]                
            if j<17:
                b = lst[j][i]*lst[j+1][i]*lst[j+2][i]*lst[j+3][i]
            if i<17 and j<17:
                c = lst[j][i]*lst[j+1][i+1]*lst[j+2][i+2]*lst[j+3][i+3]
                d = lst[j+3][i]*lst[j+2][i+1]*lst[j+1][i+2]*lst[j][i+3]
            
            tmpmax = max(a,b,c,d,tmpmax)
        j += 1
    return tmpmax
#print(get_prctmax_by_zhixian(thelist))


# Problem 12:
'''
The sequence of triangle numbers is generated by adding the natural numbers.
So the 7th triangle number would be 1 + 2 + 3 + 4 + 5 + 6 + 7 = 28.
The first ten terms would be:
1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
Let us list the factors of the first seven triangle numbers:
 1: 1
 3: 1,3
 6: 1,2,3,6
10: 1,2,5,10
15: 1,3,5,15
21: 1,3,7,21
28: 1,2,4,7,14,28
We can see that 28 is the first triangle number to have over five divisors.
What is the value of the first triangle number to have over five hundred divisors?
def is_trnum(x):
    x = (1+n)*n/2
    2x = (n+1)*n=n^2 +n
    n^2 +n-2x = 0
    n=(-1+sqrt(1+8x))/2
    if isinstance(n, int):
        return x
'''

def get_trnum(n):
    return int((1+n)*n/2)

def is_trnum(x):
    a = (math.sqrt(1+8*x)-1)/2
    return int(a)

def get_factor_list(n):
    flst = []
    for i in range(1,n):
        if n%i == 0:
            flst.append(i)    
    flst.append(n)
    if n<=0:
        print("n must large than 0")
    return flst
'''
# too slow, 只能使用必杀技了！
n=12374
while n!=0:
    yhnum = get_trnum(n)
    flst = get_factor_list(yhnum)
    ln = len(flst)
    if ln > 100:
        #print(flst)
        print(str(yhnum) + "---" + str(ln))
    if ln > 500:
        break
    n += 1
'''
print(get_trnum(12375))

#print(get_factor_list(2031120))
#print(get_prime_factor(2031120))
#print(get_factor_list(76576500))
#print(len(get_factor_list(76576500)))
#print(get_prime_factor(500))

print(is_trnum(76576500))

#print(int(math.pow(2,166)*math.pow(3,2)))

list_nxm_prdct = []
def get_nm_prdct(x):
    tmplst = []
    for i in range(x,1,-1):
        print(i)

get_nm_prdct(10)


'''
def get_nm_prdct(x):
    for i in range(x,2,-1):
        tmp = x%i
        if tmp is num: 
            add(i,tmp) to a list;
            if tmp is not a prime:
                loop again
     return a 2v-list

and each item list, elements's product is X!
'''












