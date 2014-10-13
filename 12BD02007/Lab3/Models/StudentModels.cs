using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using Lab3;
using System.Data.Linq;

namespace Lab3.Models
{
    #region Models
    public partial class Student
    {
        public Student(int id, string name) {
            this.ID = id;
            this.Name = name;
        }

        public Student(string name)
        {
            this.Name = name;
        }

        public Student(string name, decimal GPA)
        {
            this.Name = name;
            this.GPA = GPA;
        }

        public Student(int id, string name, decimal GPA)
        {
            this.ID = id;
            this.Name = name;
            this.GPA = GPA;
        }

        //public int ID {get; set;}
        //public string Name {get; set;}
    }
    #endregion
}