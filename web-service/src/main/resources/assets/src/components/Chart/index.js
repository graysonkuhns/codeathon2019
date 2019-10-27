import React from 'react';
import { PieChart, Pie, Tooltip, ResponsiveContainer, Legend } from 'recharts';

// Generate Data
function createData(name, value) {
  return { name, value };
}

const data = [
   createData('Java', 18),
   createData('JavaScript', 49),
   createData('Python', 9),
   createData('go', 24)
];



export default function Chart() {
  return (
  
    <ResponsiveContainer minWidth={"100%"} minHeight={400}>
        <PieChart
          width={400} height={400}>
          <Pie dataKey="Name" data={data} cx={200} cy={200} fill="#3f3f3f" outerRadius={80} label/>
        <Tooltip />
        </PieChart>
    </ResponsiveContainer>
  );
}