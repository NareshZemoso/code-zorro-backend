
test = r'''import aiohttp
import asyncio

url = "http://localhost:8000/api/runcode"


payload = "{\n\t\"Source\":\"#include<stdio.h>\\n int main()\\n{printf(\\\"Hello Go Lang\\\");return 0;}\",\n\t\"Language\":\"C\"\n}"
headers = {
  'Content-Type': 'application/json',
  'Authorization': 'Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJVc2VySWQiOjN9.cnc4AOTWlYaM4UV7usqZtrJKW9BKnw0F-wXVQ7M9FOY'
}


async def make_req():
    async with aiohttp.ClientSession() as session:
        async with session.post(url = url,data = payload, headers = headers) as response:
            data = await response.json()
            print(data)
            return data

loop = asyncio.get_event_loop()
loop.run_until_complete(asyncio.wait([make_req() for _ in range(100)]))'''
import timeit
print(timeit.timeit(test,number=1))