import React, {useEffect, useState} from "react";
import { apiGet } from "../utils/api";

const PersonStats = () => {
    const [stats, setStats] = useState([]);

    useEffect (() => {
        apiGet("/api/persons/statistics").then((data) => setStats(data));
    }, []);


    return (
        <div>
            <h3 className="mb-4 mt-2">Statistiky pro jednotlivé osoby</h3>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Id osoby</th>
                        <th>Název společnosti / osoby</th>
                        <th>Příjmy</th>
                    </tr>
                </thead>
                <tbody>
                    {stats.map((person) => (
                        <tr key={person.personId}>
                            <td>{person.personId}</td>
                            <td>{person.personName}</td>
                            <td>{person.revenue} Kč</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default PersonStats;