package eldarvenes;

import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by remote on 27.03.2016.
 */
public class HtmlPostEneryToWebTest extends TestCase {

    public void testGetLoginPage() throws Exception {

       HtmlPostEneryToWeb htmlPost = new HtmlPostEneryToWeb();

       htmlPost.register();

    }

    public void testGetSessionId(){
        String textToSearchIn = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"><html> <head><title>Kundesider</title> <meta http-equiv=\"content-type\" content=\"text/html; charset=iso-8859-1\"><meta http-equiv=\"expires\" content=\"-1\"><meta http-equiv= \"pragma\" content=\"no-cache\"><meta name=\"robots\" content=\"index, follow\"><meta name=\"MSSmartTagsPreventParsing\" content=\"true\"><link rel=\"stylesheet\" href=\"webuser.ressurshenter.hent_css?p_id=STANDARD.CSS\" type=\"text/css\" media=\"screen\"><link rel=\"stylesheet\" href=\"webuser.ressurshenter.hent_css?p_id=PRINT.CSS\" type=\"text/css\" media=\"print\"><style type=\"text/css\">#banner {    background: url(webuser.img.deliver_media?media=image&entry_id=903) no-repeat top center;    position: relative;    margin: 0 auto;    width: 1003px;    height: 123px;    z-index: 3;    text-align: right;}</style><!--Generelle javascript--><script type=\"text/javascript\"><!-- Script for å starta sida opp med nytt målepunkt -->   function go() {     box = document.formSelect.example;     var selTxt = box.options[box.selectedIndex].value;     var dest=\"webuser.avlesning.controlProc?\";     dest=dest + \"p_session_Id=\" + \"250538\" + \"&\"+ \"p_nyttMaalepkt=\" +  selTxt;     location.href =dest;}</script><!-- Kalender --><script type=\"text/javascript\" src=\"webuser.ressurshenter.p_get_kalender_utils\"></script><script type=\"text/javascript\" src=\"webuser.ressurshenter.p_get_kalender\"></script><script type=\"text/javascript\" src=\"webuser.ressurshenter.p_get_kalender_setup\"></script><script type=\"text/javascript\" src=\"webuser.ressurshenter.p_get_kalender_no\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"webuser.ressurshenter.p_get_kalender_css\"><script src=\"webuser.ressurshenter.hent_javascript?p_id=AVLESNING.JS\" type=\"text/javascript\"></script>  </head><body ><div id=\"wrap\">    <div id=\"banner\"></div>    <div id=\"sub\">        <div id=\"bread\">Kunde: Eldar&nbsp;Venes&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Kundenummer: 129863</div>        <div id=\"right\" >                <div id=\"hovedboks\">                <div id=\"head_hoved\"></div>                <div id=\"midt_hoved\">                    <div class=\"hovuddel\" id=\"id_hovuddel\">                        <div class=\"fjern_print\">                                                    </div>                                                                        <span class=\"h1\">Målaravlesing</span><br><br>\t<table class=\"stdFont\">      \t<tr> \t\t<td colspan=\"3\"><b>Følgjande informasjon er registrert om målaren:</b></td>         </tr>         <tr> \t\t<td>Målaradresse:</td>                 <td> 6858&nbsp;FARDAL </td>         </tr>\t            <tr>                 <td>MålepunktID:</td>                 <td>62124523</td>         </tr>                   <tr>                 <td>Målarnummer:</td>                 <td>SKR14318</td>         </tr>        <tr>                 <td colspan=\"2\">Målaren har 6 siffer før komma.<br><br></td>\t</tr>\t<tr> \t\t<td colspan=\"3\"><b>Forrige registrering</b></td>         </tr>         <tr>                 <td>Dato:</td>                 <td>25.03.2016</td>        </tr>         <tr>        \t<td>Registreringsmåte:&nbsp;&nbsp;&nbsp;</td>        \t<td>Kunde - web</td>        </tr>        <tr>                 <td>Målarstand:</td>                 <td>161193</td>\t\t<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"webuser.forbruk.controlProc?p_session_id=250538\">Vis alle avlesingar</a></td>          </tr>  </table> <br><div class=\"stdfont\">\tSkriv inn dato for ny avlesing og den avlesne målarstanden.        \tTrykk &laquo;OK&raquo; for å gå vidare til kontroll av dato og målarstand. \t<br><br>                </div>\t<div>     <form action=\"webuser.avlesning.submit\" method=\"post\" name=\"formAvles\"> <input type=\"hidden\" value=\"250538\" name=\"p_session_id\">  <table class=\"stdFont\">        <tr>         \t    <td>Avlesingsdato:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>                 <td><input type=\"text\" class=\"inputstd\" name=\"p_avlesDato\" id=\"id_avlesDato\" value=\"27.03.2016\"  onKeyUp=\"sjekkdato('id_avlesDato',event);\"><img id=\"p_avles_kalender\" name=\"p_avles_kalender\" src=\"webuser.ressurshenter.p_get_kalender_ikon\" style=\"border:none;\" alt=\"Velg dato fra kalender.Hurtigtaster i feltet:[DDMMÅÅ] = DD.MM.ÅÅÅÅ[d] = Dagens dato,[Pil opp/ned] = neste/forrige dag[Page Up/Down] = neste/forrige måned\">          <script type=\"text/javascript\">//<![CDATA[      Zapatec.Calendar.setup({        firstDay          : 1,        showOthers        : true,        step              : 1,        electric          : true,        inputField        : \"id_avlesDato\",        button            : \"p_avles_kalender\",ifFormat          : \"%d.%m.%Y\",                          daFormat          : \"%d.%m.%Y\"      });    //]]></script></td>         </tr>         <tr>                 <td>Målarstand:</td>                 <td><input type=\"text\" class=\"inputstd\" name=\"p_maalarStand\" value=\"\" onKeyPress=\"return letternumber(event);\"></td>         </tr>         <tr>                 <td colspan=\"2\"><br><input type=\"submit\" class=\"elisBtn\" value=\"OK\"></td>         </tr> </table></div>        </form> <div class=\"stdFont\">\t\t<br><b>NB!</b> Dersom du vil ha varsel om avlesing på e-post eller SMS, kan du registrere dette i &laquo;Kundeprofil&raquo;.</div>                                </div>                </div>                <div id=\"bunn_hoved\"></div>            </div>        </div>        <div id=\"meny\" class=\"fjern_print\">\t<ul id=\"id_meny\">\t\t<li><a href=\"webuser.leveranseoversikt.p_controlproc?p_session_id=250538\">Leveranseoversikt</a></li><li><a class=\"selected_meny\" href=\"webuser.avlesning.controlProc?p_session_id=250538\">Målaravlesing</a></li><li><a href=\"webuser.forbruk_mnd.controlproc?p_session_id=250538\">Forbruk</a></li><li><a href=\"webuser.kundeprofil.controlproc?p_session_id=250538\">Kundeprofil</a></li><li><a href=\"webuser.proveavr.controlProc?p_session_id=250538\">Prøveavrekning</a></li><li><a href=\"webuser.fakturaforklaring.controlProc?p_session_id=250538\">Fakturaforklaring</a></li><li><a href=\"webuser.kontakt.controlProc?p_session_id=250538\">Kontaktskjema</a></li><li><a href=\"webuser.pkg_fakt_reskontro.p_controlproc?p_session_id=250538\">Faktura</a></li><li><a href=\"webuser.login.remove_cookie?p_session_id=250538\">Logg ut</a></li>\t\t<li style=\"height:0px;\"class=\"bunn\"></li>\t\t\t\t\t\t\t</ul></div>    </div>    <div id=\"fot\" class=\"fjern_print\"><div style=\"margin-left:auto; margin-right:auto;\"><table class=\"footer\">  <tr>    <td></td>  </tr></table>  </div></div></div><div id=\"navtxt\" class=\"navtext\" style=\"position:absolute;z-index:2; top:-100px; left:0px; visibility:hidden\"></div></body></html>\n";

        HtmlPostEneryToWeb htmlPostEneryToWeb = new HtmlPostEneryToWeb();
        Assert.assertEquals(true, "250538".equals(htmlPostEneryToWeb.getSessionId(textToSearchIn)));

    }

    public void testLoginToWeb(){
        HtmlPostEneryToWeb htmlPostEneryToWeb = new HtmlPostEneryToWeb();
        try {
            htmlPostEneryToWeb.login();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testSendAvlesing(){
        HtmlPostEneryToWeb htmlPostEneryToWeb = new HtmlPostEneryToWeb();
        try {
            htmlPostEneryToWeb.login();
            htmlPostEneryToWeb.sendAvlesing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGodkjennAvlesing(){
        HtmlPostEneryToWeb htmlPostEneryToWeb = new HtmlPostEneryToWeb();
        try {
            htmlPostEneryToWeb.login();
            htmlPostEneryToWeb.sendAvlesing();
            htmlPostEneryToWeb.godkjennAvlesing();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}