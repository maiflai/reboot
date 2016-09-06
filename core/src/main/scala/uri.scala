package dispatch

/** URI representation with raw parts, so  */
case class RawUri(
  scheme: Option[String],
  userInfo: Option[String],
  host: Option[String],
  port: Option[Int],
  path: Option[String],
  query: Option[String],
  fragment: Option[String]
) {
  def toUri = new Uri(
    (scheme.map { _ + ":" } ::
     Some("//") ::
     userInfo.map { _ +  "@" } ::
     host ::
     port.map { ":" + _ } ::
     path ::
     query.map { "?" + _ } ::
     fragment.map { "#" + _ } ::
     Nil
   ).flatten.mkString)
  override def toString = toUri.toASCIIString
}

object RawUri {
  def apply(str: String): RawUri = RawUri(new Uri(str))
  def apply(subject: Uri): RawUri = RawUri(
    scheme = Option(subject.getScheme),
    userInfo = Option(subject.getRawUserInfo),
    host = Option(subject.getHost),
    port = Some(subject.getPort).filter( _ != -1),
    path = Option(subject.getRawPath),
    query = Option(subject.getRawQuery),
    fragment = Option(subject.getRawFragment)
  )
}

object UriEncode {
  def path(pathSegment: String, encoding: String = "UTF-8") = {
    java.net.URLEncoder.encode(pathSegment, encoding).replace("+", "%20")
  }
}
