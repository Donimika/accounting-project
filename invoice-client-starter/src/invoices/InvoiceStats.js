import React, {useEffect, useState} from "react";
import { apiGet } from "../utils/api";


const InvoiceStats = () => {
    const [stats, setStats] = useState({ currentYearSum: 0, allTimeSum: 0, invoicesCount: 0 })

    useEffect(() => {
        apiGet("/api/invoices/statistics").then((data) => setStats({
            currentYearSum: data.currentYearSum,
            allTimeSum: data.allTimeSum,
            invoicesCount: data.invoicesCount,
        }));
    }, []);

    return (
        <div>
            <h3 className="mb-4 mt-2">Statistiky</h3>
            <table className="table table-bordered">
                <thead>
                    <tr>
                        <th>Součet za aktuální rok</th>
                        <th>Součet za celé období</th>
                        <th>Počet faktur</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>{stats.currentYearSum} Kč</td>
                        <td>{stats.allTimeSum} Kč</td>
                        <td>{stats.invoicesCount}</td>
                    </tr>
                </tbody>
            </table>

        </div>
    )
}

export default InvoiceStats;