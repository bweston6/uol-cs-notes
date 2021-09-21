---
title: COMP211 - Lectures
layout: article
aside:
 toc: true
sidebar:
 nav: comp211
---
{% for post in site.posts %}
{% if post.tags contains "Lectures" and post.tags contains "COMP211" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
