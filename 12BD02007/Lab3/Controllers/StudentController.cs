using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using Lab3.Models;
using System.Data.Linq;

namespace Lab3.Controllers
{
    public class StudentController : Controller
    {
        //static List<Student> students = new List<Student>();
        static DataContext dc = new Lab3DataContext();
        static Table<Student> t = dc.GetTable<Student>();


        //
        // GET: /Student/

        public ActionResult Index()
        {
            return View(t.ToList<Student>());
        }

        public ActionResult Create(FormCollection FormColl)
        {
            var cs = FormColl.GetValues("CreateStudent");
            try
            {
                t.InsertOnSubmit(new Student(cs[0], Convert.ToDecimal(cs[1])));
            }
            catch (Exception e) {
                t.InsertOnSubmit(new Student(cs[0], Convert.ToDecimal(cs[1].Replace('.', ','))));
            }
       
            dc.SubmitChanges();
            return Redirect("/");
        }

        public ActionResult Edit(FormCollection FormColl)
        {
            var cs = FormColl.GetValues("Edited");

            Student updated = t.ToList<Student>().Find(
            delegate(Student std)
            {
                return std.ID == Convert.ToInt32(cs[0]);
            }
            );

            updated.Name = cs[1];
            updated.GPA = Convert.ToDecimal(cs[2]);

            dc.SubmitChanges();

            return Redirect("/");
        }

        public ActionResult Delete(FormCollection FormColl)
        {
            var cs = FormColl.GetValues("Deleted");

            Student deleted = t.ToList<Student>().Find(
            delegate(Student std)
            {
                return std.ID == Convert.ToInt32(cs[0]);
            }
            );
            t.DeleteOnSubmit(deleted);
            dc.SubmitChanges();

            return Redirect("/");
        }


    }
}
