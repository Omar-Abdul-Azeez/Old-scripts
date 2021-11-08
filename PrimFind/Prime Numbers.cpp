#include <iostream>
#include <fstream>
#include <string>
#include <list>
#include <vector>
#include <math.h>
using namespace std;

vector<long long> calc_primes(long long end, vector<long long> pre_primes)
{
	long long start = pre_primes.back() + 2;
	long long len = (end - start + 1) & 1 ? (end - start + 1) / 2 + 1 : (end - start + 1) / 2;
	long long max_p = sqrt(end);
	vector<long long> sieve_pre_primes;
	vector<bool> sieve(len);
	for (long long j = 1; j<pre_primes.size(); j++) {
		if (pre_primes[j] > max_p)
			break;
		for (long long i = start + (start % pre_primes[j] == 0 ? 0 : pre_primes[j] - start % pre_primes[j]) + ((start + (start % pre_primes[j] == 0 ? 0 : pre_primes[j] - start % pre_primes[j])) & 1 ? 0 : pre_primes[j]); i <= end; i += pre_primes[j]+pre_primes[j]) {
			sieve[(i - start)/2] = true;
		}
	}
	vector<long long> primes;
	for (long long i = 0; i < len; i++) {
		if (!sieve[i]) {
			long long p = start + 2*i;
			primes.push_back(p);
			for (long long j = start + (start % p == 0 ? 0 : p - start % p) + ((start + (start % p == 0 ? 0 : p - start % p)) & 1 ? 0 : p); j <= end; j += p+p) {
				sieve[(j - start)/2] = true;
			}
		}
	}
	return primes;
}

int main()
{
	vector<long long> pre_primes;
	vector<long long> primes;
	string line;
	ifstream ifile("Primes.txt");
	while (line.compare("") != 0) {
		pre_primes.push_back(stoll(line));
	}
	ofstream ofile("Primes.txt", ios_base::app);
	long long inp;
	if (!pre_primes.size()) {
		pre_primes.push_back(2);
		pre_primes.push_back(3);
		ofile << 2 << "\n";
		ofile << 3 << "\n";
	}
	cout << "ENTER THE END OF THE RANGE FOR PRIMES CALCULATION!!!\n>";
	cin >> inp;
	while (inp > 1) {
		if (inp < pre_primes.back() + 2) {
			cout << "Make it larger than " << pre_primes.back() + 1 << " thx.\n>";
			cin >> inp;
			continue;
		}
		primes = calc_primes(inp, pre_primes);
		for (long long p : primes) {
			ofile << p << "\n";
		}
		ofile.flush();
		for (long long p : primes) {
			pre_primes.push_back(p);
		}
		cout << "FINISHED CALCULATIING! WE ARE CURRENTLY STANDING AT " << pre_primes.size() << " PRIMES WITH " << pre_primes.back() << " AS THE LAST DATE!!!\n>";
		cin >> inp;
	}
}
