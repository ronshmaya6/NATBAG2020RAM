import subprocess

from flask import Flask, request

app = Flask("my_app1")

@app.route("/flights")
def flights():

    return subprocess.check_output(["java", "-classpath", "C:/Users/רון שמעיה/eclipse-workspace/NATBAG2020/bin", "Flight.Program",
                                    request.args.get('outformat'),
                                    request.args.get('flight'),
                                    request.args.get('country'),
                                    request.args.get('city'),
                                    request.args.get('airport'),
                                    request.args.get('airline'),
                                    request.args.get('day1'),
                                    request.args.get('month1'),
                                    request.args.get('year1'),
                                    request.args.get('day2'),
                                    request.args.get('month2'),
                                    request.args.get('year2'),
                                    request.args.get('dayinweek1'),
                                    request.args.get('dayinweek2')])

app.run(port=8027, host="0.0.0.0")
